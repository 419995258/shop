var aboutAppmodule = angular.module('aboutApp', []);

aboutAppmodule.controller('aboutController', function($scope, $http, $rootScope, $stateParams, $state,$sce) {
	////////////////////////////////////////////////////////判断当前页	
	function init() {		
		if ($state.current.name == 'about') {
			$('.qz_content > a').removeClass('active');
			$('#btn_about').addClass('active');
		};
		queryNewsById();
	};
	
	$scope.aboutC = {};
	function queryNewsById() {
		var param = {};
        $http({
            url: '../otherManageC/showAboutData',
            method: 'POST',
            data: param
        }).success(function (data) {
			if (!verifyIsNull(data.content)) {
				data.content = $sce.trustAsHtml(data.content);
			}
        	$scope.aboutC = data;
        }).error(function (response) {
        });
    };


	init();
});