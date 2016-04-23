cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "file": "plugins/cordova-plugin-whitelist/whitelist.js",
        "id": "cordova-plugin-whitelist.whitelist",
        "runs": true
    },
    {
        "file": "js/cordova/services/gcm.service.js",
        "id": "gcm.service",
        "clobbers": [
            "gcm.module"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "cordova-plugin-whitelist": "1.2.1",
    "com.example.plugin": "0.0.1"
};
// BOTTOM OF METADATA
});