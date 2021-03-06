window.HeaderView = Backbone.View.extend({

	events: {
		"click button#btnFBSignup" : "FBSignup"
	},

    initialize: function () {
        this.render();
    },

    render: function () {
        $(this.el).html(this.template());
        return this;
    },

    selectMenuItem: function (menuItem) {
        $('.nav li').removeClass('active');
        if (menuItem) {
            $('.' + menuItem).addClass('active');
        }
    },
    
    FBSignup: function () {
			user = new FBUser();
			user.login();    	
    	} 
    
});