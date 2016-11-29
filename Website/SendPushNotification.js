// External dependencies
var express = require('express');
var sendPush = express.Router();
var PushNotifications = require('bluemix-push-notifications').PushNotifications;
var Notification = require('bluemix-push-notifications').Notification;

// Displays current version of server APIs
sendPush.get('/', function(req, res) {
    var myPushNotifications = new PushNotifications(PushNotifications.Region.US_SOUTH, "2e776d75-1609-4fc8-8938-4d3095dda51b", "8985eb51-a606-4519-adb3-ee0435f2b869");
    
    var notificationExample = new Notification("your order is ready");
    
    myPushNotifications.send(notificationExample, function(error, response, body) {
        console.log("Error: " + error);
        console.log("Response: " + JSON.stringify(response));
        console.log("Body: " + body);
    });
    res.status(200).json({
        sendPushService: "running"
        
    });
});

module.exports = sendPush;
