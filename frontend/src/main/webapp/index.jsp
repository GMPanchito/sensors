<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<link rel="stylesheet" href="style.css" />
<link rel="stylesheet" href="/lib/openlayers/theme/default/style.tidy.css" />
<script type="text/javascript" src="/js/osm.js" ></script>
<script type="text/javascript" src="/js/sensors.js" ></script>
<script type="text/javascript" src="/lib/openlayers/OpenLayers.js" ></script>
<div class="container-fluid root">
    <div id="map_controls" class="col-md-2 map_controls">
        <div class="input-group">
            <span class="input-group-addon">Region</span>
            <select id="region" class="form-control">
                <option value="saint_petersburg">List</option>
                <option value="sochi">Of</option>
                <option value="ukraine">Predefined</option>
                <option value="world">Regions</option>
            </select>
        </div>
        <div class="input-group">
            <span class="input-group-addon">Sensor type</span>
            <select id="sensor_type" class="form-control">
                <option value="temperature">Temperature</option>
                <option value="humidity">Humidity</option>
                <option value="all">All</option>
            </select>
        </div>
        <div class="input-group">
            <button id="reload" type="button" class="btn btn-default">Reload</button>
            <img id="loading" src="/img/loading.gif" />
        </div>
        <div class="posts">
            <div id="posts">
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-md-10">
        <div id="demoMap" class="map"></div> 
    </div>
</div>
<%@include file="/WEB-INF/jspf/counters.jspf" %>
<%@include file="/WEB-INF/jspf/footer.jspf" %>