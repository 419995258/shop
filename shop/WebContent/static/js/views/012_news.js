var aboutAppmodule = angular.module('newsApp', []);

aboutAppmodule.controller('newsController', function($scope, $http, $rootScope, $stateParams, $state,$sce) {
	////////////////////////////////////////////////////////判断当前页	
	function init() {		
		if ($state.current.name == 'news') {
			$('.qz_content > a').removeClass('active');
			$('#btn_news').addClass('active');
		};
		
	};
	

	init();
});