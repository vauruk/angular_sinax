angular.module('sinax_music', [])
//.value('urlBase', 'http://localhost:8080/angular_sinax/rest/ws_music/')
.value('urlBase', 'http://vauruk.no-ip.info:8080/angular_sinax/rest/ws_music/')
.controller("MusicController", function ($http, urlBase){
	var self = this;
	
	self.name = "Sistema de Controle de Musica";
	self.music = undefined;
	self.nameMusic = "";
	
	self.musics = [];
	self.artists = []; 
	
	self.novo = function () {
		self.music = {};
		self.loadArtists();
	}
	
	self.selectedArtist = function (artist){
		self.artist = artist;
	}
	self.selectedMusics = function (){
		if(self.nameMusic.length > 0 && self.nameMusic.length >= 3 ){
			self.loadMusicsByName(self.nameMusic);
		}else {
			self.loadMusics();
		}
	}
	
	self.salvar = function () {
		let metodo = 'PUT';
			
		$http({
			method: metodo,
			url: urlBase+'salvar_music',
			data: self.music
		}).then( function successCallback ( response ) {
			self.loadMusics();
			self.music = undefined;
		}, function errorCallback( response ){
			self.ocorreuErro();	
		});
				
	}
	self.alterar = function (music) {
		self.music = music;
		
	}
	self.deletar = function (music) {
		$http({
			method: 'DELETE',
			url: urlBase+'deletar_music/'+music.id
		}).then( function successCallback ( response ) {
			self.loadMusics();	
		}, function errorCallback( response ){
			self.ocorreuErro();
		});
	}
	
	self.ocorreuErro = function (){
		alert("Erro de callback");
	} 
	
	self.loadArtists = function () {
		$http({
			method: 'GET',
			url: urlBase+'list_artist'
		}).then( function successCallback ( response ) {
			self.artists = response.data;
		}, function errorCallback( response ){
			self.ocorreuErro();	
		});
	}
	
	self.loadMusics = function (){
		$http({
			method: 'GET',
			url: urlBase+'list_music'
		}).then( function successCallback ( response ) {
			self.musics = response.data;
		}, function errorCallback( response ){
			self.ocorreuErro();	
		});
	}
	
	self.loadMusicsByName = function (nameMusic){
		$http({
			method: 'GET',
			url: urlBase+'list_music/'+nameMusic
		}).then( function successCallback ( response ) {
			self.musics = response.data;
		}, function errorCallback( response ){
			self.ocorreuErro();	
		});
	}
	
	self.activate = function(){
		self.loadMusics();
	}
	
	self.activate();

});



