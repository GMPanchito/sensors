<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<link rel="stylesheet" href="style.css" />
<link rel="stylesheet" href="/lib/openlayers/theme/default/style.tidy.css" />
<script type="text/javascript" src="/js/osm.js" ></script>
<script type="text/javascript" src="/js/sensors.js" ></script>
<script type="text/javascript" src="/lib/openlayers/OpenLayers.js" ></script>

<div id="demoMap" class="map"></div> 

<%@include file="/WEB-INF/jspf/counters.jspf" %>
<%@include file="/WEB-INF/jspf/footer.jspf" %>