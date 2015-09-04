(function() {
	
	var app = angular.module('Store',[]);
	
	app.controller('StoreController', function() {
		this.products = gems;
	});
	
	app.controller('ReviewController',['$log','$window', '$scope',function($log,$window,$scope) {
		this.review = {};
		$scope.feedback = 'review added';
		
		this.addReview = function(product) {
			product.reviews.push(this.review);
			this.review = {};
			$window.alert(feedback);			
		};
		
		$scope.doGreeting = function() {
			$window.alert(feedback);
		};

	}]);
	
	var gems = [ {
		name : 'Gem 1',
		price : 1.00,
		description : 'This is Gem1 Description',
		isSoldOut : true,
		images :[ {
			full : 'Gem1Full1.jpg',
			thumb : 'Gem1Thumb1.jpg'
			
		}, {
			full : 'Gem1Full2.jpg',
			thumb : 'Gem1Thumb2.jpg'			
		}],
		reviews :[{
			title : 'Gem1 Review 1 Title',
			description : 'Gem1 Review 1 Description',
			author : 'abhowmic@gem1r1.com'
		},{
			title : 'Gem1 Review 2 Title',
			description : 'Gem1 Review 2 Description',
			author : 'abhowmic@gem1r2.com'			
		}]
		
	}, {
		name : 'Gem 2',
		price : 1.25,
		description : 'This is Gem2 Description',
		isSoldOut : false,
		images :[ {
			full : 'Gem2Full1.jpg',
			thumb : 'Gem2Thumb1.jpg'
			
		}, {
			full : 'Gem2Full2.jpg',
			thumb : 'Gem2Thumb2.jpg'			
		}],
		reviews :[{
			title : 'Gem2 Review 1 Title',
			description : 'Gem2 Review 1 Description',
			author : 'abhowmic@gem2r1.com'
		},{
			title : 'Gem2 Review 2 Title',
			description : 'Gem2 Review 2 Description',
			author : 'abhowmic@gem2r2.com'			
		}]		
	}];
	
})();