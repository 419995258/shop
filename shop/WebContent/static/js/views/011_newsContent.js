var aboutAppmodule = angular.module('newsContentApp', []);

aboutAppmodule.controller('newsContentController', function($scope, $http, $rootScope, $stateParams, $state,$sce) {
	////////////////////////////////////////////////////////判断当前页	
	function init() {		
		if ($state.current.name == 'newsContent') {
			$('.qz_content > a').removeClass('active');
			$('#btn_news').addClass('active');
		};
		
	};
	
	$scope.newsC = {};
	$scope.newsC = {'content':'在国务院新闻办29日举行的发布会上，水利'};
	
	init();
});