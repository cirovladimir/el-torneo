window.FBUser = Backbone.Model.extend({

	initialize: function(){
		self = this;
	},
	
	defaults: {
			
	},

	login: function(){
		FB.login(function(response) {
			console.log('fb-login-response: '+response.authResponse);
			if (response.authResponse) {
            // connected
			} else {
            // cancelled
		}
    });
	},
	
	verifyPermissions: function(){
		FB.getLoginStatus(function(response) {
			console.log('fb status:'+response.status);
  			if (response.status === 'connected') {
    			// connected
  			} else if (response.status === 'not_authorized') {
    			//self.login();
  			} else {
    			// not_logged_in
    			//self.login();
  			}
 		});
	}
});