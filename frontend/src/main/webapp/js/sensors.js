function getFeatureNames(features) {
    var result = "";
    for (var i in features) {
        if (features.hasOwnProperty(i)) {
            result += features[i].type + "<br/>";
        }
    }
    return result;
}