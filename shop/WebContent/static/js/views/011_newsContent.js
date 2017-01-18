var aboutAppmodule = angular.module('newsContentApp', []);

aboutAppmodule.controller('newsContentController', function($scope, $http, $rootScope, $stateParams, $state,$sce) {
	////////////////////////////////////////////////////////判断当前页	
	function init() {		
		if ($state.current.name == 'newsContent') {
			$('.qz_content > a').removeClass('active');
			$('#btn_news').addClass('active');
		};
		
	};
	

	init();
});