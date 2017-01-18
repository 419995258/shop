var aboutAppmodule = angular.module('aboutApp', []);

aboutAppmodule.controller('aboutController', function($scope, $http, $rootScope, $stateParams, $state,$sce) {
	////////////////////////////////////////////////////////判断当前页	
	function init() {		
		if ($state.current.name == 'about') {
			$('.menu_btn').removeClass('active');
			$('#btn_about').addClass('active');
		};
		
	};
	
	var um = UM.getEditor('myEditor');
	
	init();
});