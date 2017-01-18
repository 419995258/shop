var aboutAppmodule = angular.module('newsApp', []);

aboutAppmodule.controller('newsController', function($scope, $http, $rootScope, $stateParams, $state,$sce) {
	////////////////////////////////////////////////////////判断当前页	
	function init() {		
		if ($state.current.name == 'news') {
			$('.qz_content > a').removeClass('active');
			$('#btn_news').addClass('active');
		};
		
	};
	
	$scope.x = {};
	$scope.x = {'title':'关于心羽基金','createTimeStr':'在国务院新闻办29日举行的发布会上，水利'};
	
	init();
});