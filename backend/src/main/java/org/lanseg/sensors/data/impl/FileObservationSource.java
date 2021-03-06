package org.lanseg.sensors.data.impl;

import org.lanseg.sensors.data.api.ObservationSource;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lanseg.sensors.data.Observation;

/**
 *
 * @author lans
 */
public class FileObservationSource implements ObservationSource {

    private static final Logger LOG = Logger.getLogger("DataSource");
    private static final int RECORD_SIZE = 4;
    private static final int METADATA_SIZE = 8 * 2;
    private static final int DEFAULT_RECORD_COUNT = 365 * 24 * 60;
    private static final int ROUND_RANGE = 10000;

    private String filename;
    private RandomAccessFile file;
    private long minTime = 0;
    private long maxTime = 0;

    public FileObservationSource() {
    }

    public FileObservationSource(String filename) throws IOException {
        this.filename = filename;
        File sourceFile = new File(filename);
        boolean firstTimeWrite = !sourceFile.exists();
        file = new RandomAccessFile(sourceFile, "rw");
        if (firstTimeWrite) {
            createFile(DEFAULT_RECORD_COUNT);
        }
        file.seek(0);
        minTime = file.readLong();
        maxTime = file.readLong();
    }

    private void createFile(int recordCount) throws IOException {
        file.setLength(METADATA_SIZE + recordCount * RECORD_SIZE);
        for (long i = 0; i < file.length(); i++) {
            file.write(0xFF);
        }
        file.seek(0);
        file.writeLong(System.currentTimeMillis());
        file.writeLong(System.currentTimeMillis());
    }

    private void setMaxTime(long maxTime) throws IOException {
        file.seek(8);
        file.writeLong(maxTime);
    }

    public long roundTime(long time) {
        if (time <= minTime) {
            return minTime;
        } else if (time >= maxTime) {
            return maxTime;
        }
        return 0;
    }

    @Override
    public long getMinTime() {
        return minTime;
    }

    @Override
    public long getMaxTime() {
        return maxTime;
    }

    @Override
    public List<Observation> getObservations(long fromTime, long toTime) {
        if (minTime == 0 || toTime < fromTime) {
            return Collections.EMPTY_LIST;
        }
        if (fromTime < minTime) {
            fromTime = minTime;
        }
        if (toTime > maxTime) {
            toTime = maxTime;
        }
        long startPos = RECORD_SIZE * (fromTime - minTime) / 1000 + METADATA_SIZE;
        long endPos = RECORD_SIZE * (toTime - minTime) / 1000 + METADATA_SIZE;
        List<Observation> result = new ArrayList<>();
        byte value[] = new byte[RECORD_SIZE];
        try {
            for (long pos = startPos; pos <= endPos; pos += RECORD_SIZE) {
                file.read(value);
                int asInt = (value[3] & 0xFF)
                        | ((value[2] & 0xFF) << 8)
                        | ((value[1] & 0xFF) << 16)
                        | ((value[0] & 0xFF) << 24);
                if (asInt != 0xFFFFFFFF) {
                    result.add(new Observation(pos * 1000 + minTime,
                            (double) Math.round(ROUND_RANGE * Float.intBitsToFloat(asInt)) / ROUND_RANGE));
                }
            }
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error reading data: {1}", ex);
        }

        return result;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public void addObservations(List<Observation> data) {
        try {
            for (Observation record : data) {
                addRecord(record);
            }
            setMaxTime(maxTime);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error writing data: {1}", ex);
        }
    }

    @Override
    public void addObservation(Observation record) {
        try {
            addRecord(record);
            setMaxTime(maxTime);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error writing data: {1}", ex);
        }
    }

    private long getLocation(long time) {
        return ((time - minTime) / 1000 / RECORD_SIZE) * RECORD_SIZE + METADATA_SIZE;
    }

    private void addRecord(Observation record) throws IOException {
        long pos = getLocation(record.getTime());
        LOG.log(Level.INFO, "Adding observation: {1} at {2}", 
                new Object[] {record, pos});
        file.seek(pos);
        file.writeFloat(Double.valueOf(record.getValue()).floatValue());
        if (record.getTime() > maxTime) {
            maxTime = record.getTime();
        }
    }

    public void close() {
        try {
            file.close();
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, "Error closing data file: {1}", ex);
        }
    }
}
