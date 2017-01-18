var aboutAppmodule = angular.module('aboutApp', []);

aboutAppmodule.controller('aboutController', function($scope, $http, $rootScope, $stateParams, $state,$sce) {
	////////////////////////////////////////////////////////判断当前页	
	function init() {		
		if ($state.current.name == 'about') {
			$('.qz_content > a').removeClass('active');
			$('#btn_about').addClass('active');
		};
		
	};
	
	$scope.aboutC = {};
	$scope.aboutC = {'title':'关于心羽基金','texts':'在国务院新闻办29日举行的发布会上，水利'};

	init();
});