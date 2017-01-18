angular.module('phonecatFilters', ['ngCookies', 'pascalprecht.translate']);

var indexApp=angular.module('indexApp', ['ui.router','ui.bootstrap','phonecatFilters','tm.pagination','ngCookies','pascalprecht.translate',
'homeApp','cardApp','orderApp','centerApp','loginApp','registerApp','passwordRecoveryApp','passwordRecovery2App','aboutApp','newsContentApp','newsApp']).config(
		function($stateProvider, $urlRouterProvider) {
			$urlRouterProvider.otherwise('/home');
			$stateProvider.state('home', {//首页
				url : '/home',
				templateUrl : '002_home.html',
				cache:'false',
				controller: 'homeController'				
			}).state('card', {//购物车
				url : '/card',
				templateUrl : '003_card.html',
				cache:'false',
				controller: 'cardController'				
			}).state('order', {//订单
				url : '/order',
				templateUrl : '004_order.html',
				cache:'false',
				controller: 'orderController'				
			}).state('center', {//用户中心
				url : '/center',
				templateUrl : '005_center.html',
				cache:'false',
				controller: 'centerController'				
			}).state('login', {//登录
				url : '/login',
				templateUrl : '006_login.html',
				cache:'false',
				controller: 'loginController'				
			}).state('register', {//注册
				url : '/register',
				templateUrl : '007_register.html',
				cache:'false',
				controller: 'registerController'				
			}).state('passwordRecovery', {//找回密码
				url : '/passwordRecovery',
				templateUrl : '008_passwordRecovery.html',
				cache:'false',
				controller: 'passwordRecoveryController'			
			}).state('passwordRecovery2', {//找回密码
				url : '/passwordRecovery2',
				templateUrl : '009_passwordRecovery2.html',
				cache:'false',
				controller: 'passwordRecovery2Controller'			
			}).state('about', {//关于超市
				url : '/about',
				templateUrl : '010_about.html',
				cache:'false',
				controller: 'aboutController'			
			}).state('newsContent', {//新闻内容
				url : '/newsContent',
				templateUrl : '011_newsContent.html',
				cache:'false',
				controller: 'newsContentController'			
			}).state('news', {//新闻中心
				url : '/news',
				templateUrl : '012_news.html',
				cache:'false',
				controller: 'newsController'			
			});
		})

	.factory('DataInfo', function() {
	return {
		name: "1"
	};
});


indexApp.controller('indexController', function($scope, $http,$cookies, $rootScope,$state,$stateParams) {
	////////////////////////////////////////////////测试数据
	$scope.user={};
	$scope.friends=[];
	$scope.friends=[{'friendName':'淘宝','href':'http://www.taobao.com/'},{'friendName':'京东','href':'http://www.jd.com/'},{'friendName':'唯品会','href':'http://www.vip.com/'},{'friendName':'百度','href':'http://www.baidu.com/'}];
	
	function init() {
		ckLogined();
		queryAdd();
	};
	
	init();
	/** ******************************************验证登录**************************************** */
	// 验证登录
	function ckLogined() {
		$http({
			method : "POST",
			url : "../userC/ckUserLogined",
		}).success(function(data, status) {
				$scope.user = data.result.user;
				//console.log($scope.user.username);
		});
	}
	
	/** ******************************************登出**************************************** */
	$scope.loginOut = function(){
		$http({
			method : "POST",
			url : "../userC/logout",
		}).success(function(data, status) {
			$scope.user = {};
			$state.go("home", {}, { reload: true });
			
		});
	};
	/** ******************************************查询访问人数，交易额度**************************************** */
	function queryAdd(){
		$http({
			method : "POST",
			url : "../userC/queryAdd",
		}).success(function(data, status) {
			console.log("查询成功");
		});
	};
});