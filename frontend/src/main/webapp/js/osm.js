/*
 sensor: id, location, features
 
 */

$(function() {
    fromProjection = new OpenLayers.Projection("EPSG:4326");   // Transform from WGS 1984
    toProjection = new OpenLayers.Projection("EPSG:900913"); // to Spherical Mercator Projection
    map = new OpenLayers.Map("demoMap");
    map.addLayer(new OpenLayers.Layer.OSM());
    map.zoomToMaxExtent();

    var styleMap = new OpenLayers.StyleMap(
            {
                'default': new OpenLayers.Style({
                    'pointRadius': 4,
                    'strokeWidth': 4,
                    'fillColor': '#FFA500',
                    'fillOpacity': 0.5,
                    'strokeColor': 'orange'
                }),
                'select': new OpenLayers.Style({
                    'pointRadius': 4,
                    'strokeWidth': 4,
                    'fillOpacity': 0.5,
                    'fillColor': '#00FFA5',
                    'strokeColor': 'blue'
                })
            });

    pois = new OpenLayers.Layer.Vector("POIs", {
        styleMap: styleMap
    });
    map.addLayer(pois);
    pois.events.on({
        'featureselected': onFeatureSelect,
        'featureunselected': onFeatureUnselect
    });

    var selectControl = new OpenLayers.Control.SelectFeature(pois);
    map.addControl(selectControl);
    selectControl.activate();

    function onFeatureSelect(evt) {
        var feature = evt.feature;
        var a = feature.attributes;
        popup = new OpenLayers.Popup.FramedCloud("chicken",
                feature.geometry.getBounds().getCenterLonLat(),
                null, 'Fatures:<br/> ' +
                a.text,
                null, true, function(evt) {
                    selectControl.unselect(feature);
                });
        feature.popup = popup;
        map.addPopup(popup);
    }

    function onFeatureUnselect(evt) {
        var feature = evt.feature;
        map.removePopup(feature.popup);
        feature.popup.destroy();
        feature.popup = null;
    }
});

function updateUser(userName) {
    $("#user").val(userName);
}

function getSensorData(params) {
    $("#loading").show();
    $("#posts").html("");
    while (map.popups.length) {
        map.removePopup(map.popups[0]);
    }
    pois.removeAllFeatures();
    $.ajax({
        url: "/rest/sensors/list" /*+ params*/,
        success: function(data) {
            var counter = 0;
            for (var i = 0; i < data.length; i++) {
                var sensor = data[i];
                var details = data[i].details;
                var point = details.location;
                var lonlat = new OpenLayers.LonLat(point.lon, point.lat).transform(fromProjection, toProjection);
                pt = new OpenLayers.Feature.Vector(new OpenLayers.Geometry.Point(lonlat.lon, lonlat.lat));
                pt.attributes["text"] = details.title + "<br/>" + details.description;
                pt.attributes["id"] = sensor.id;
                pt.attributes["author"] = point.author;
                pt.attributes["authorName"] = point.authorName;
                pois.addFeatures([pt]);
                if (counter++ < 100) {
                    $("#posts").html($("#posts").html() + "<div class='post'>" + sensor.id + ":" + details.title + "</div>");
                }
            }
            $("#loading").hide();
        },
        failure: function(data) {
            $("#loading").hide();
        }
    });
}

$(function() {
    getSensorData("world");

    $("#region").change(function() {
        getSensorData("world");
    });

    $("#reload").click(function() {
        getSensorData("world");
    });
});

function selectRegion(val) {
    getTweets(val.value, document.getElementById("text").value);
}