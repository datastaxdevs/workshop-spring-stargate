// SVG: http://stargate.wikia.com/wiki/Glyph

var sounds = {}
//var path = 'https://cdn.rawgit.com/ManzDev/cursos-assets/gh-pages/css3/audio/';
var path = 'https://manzdev.github.io/cursos-assets/css3/audio/';
var title = document.querySelector('.title');
var listChevrons = document.querySelectorAll('#stargate img');

// ChevronClosed sounds
sounds.chevronClosed = {
  audio: [new Audio(path + 'chev_usual_1.mp3'),
          new Audio(path + 'chev_usual_2.mp3'),
          new Audio(path + 'chev_usual_3.mp3'),
          new Audio(path + 'chev_usual_4.mp3'),
          new Audio(path + 'chev_usual_5.mp3'),
          new Audio(path + 'chev_usual_6.mp3'),
          new Audio(path + 'chev_usual_7.mp3')]
}
sounds.chevronClosed.play = function(n = -1) {
  if (n == -1)
    var n = Math.floor(Math.random() * this.audio.length);
  this.audio[n].currentTime = 0;
  this.audio[n].play();
}

// Stargate sounds
sounds.gateRoll = new Audio(path + 'roll.mp3');
sounds.gateSpin = new Audio(path + 'spin.mp3');
sounds.fail = new Audio(path + 'fail.mp3');
sounds.gateClose = new Audio(path + 'eh_usual_close.mp3');
sounds.gateOpen = new Audio(path + 'eh_usual_open.mp3');
sounds.gateSpin.loop = true;
sounds.gateSpin.addEventListener('timeupdate', function() {
  if (this.currentTime > this.duration - .3)
    this.currentTime = 0;
});

// WormHole Loop sounds
sounds.wormHole = new Audio(path + 'wormhole.mp3');
sounds.wormHole.loop = true;
sounds.wormHole.addEventListener('timeupdate', function() {
  if (this.currentTime > this.duration - .6)
    this.currentTime = 0;
});

// Arrows
var arrows = document.querySelectorAll('.arrow');
[].forEach.call(arrows, function(i) {
  i.addEventListener('click', function() {
    
    if (!this.classList.contains('on')) {
      sounds.chevronClosed.play();
      this.classList.add('on');
      
      if (stargate.getChevronsOpened() == 8) {
        stargate.startMove();
        //stargate.title = title.innerHTML;
        //title.innerHTML = '';
      }
      
      //var n = Math.floor(Math.random() * listChevrons.length);
      //title.innerHTML += listChevrons[n].outerHTML;
    
      if ((stargate.isOpen() == false) && (stargate.isReady() == true))
        setTimeout(function() {
          stargate.enableWormHole();
        }, 1000);
    }
   
  });
});

var stargate = {
  
  CHAPAHAI_OPEN: false,
  
  isOpen: function() {
    return this.CHAPAHAI_OPEN;
  },
  
  isReady: function() {
    return (document.querySelectorAll('.arrow:not(.on)').length === 0);
  },
  
  getChevronsOpened: function() {
    return document.querySelectorAll('.arrow:not(.on)').length;
  },

  // Comienza a mover el Stargate (20 seg máx.)
  startMove: function() {
    document.querySelector('#stargate').classList.add('on'); 
    sounds.gateRoll.volume = .5;
    sounds.gateRoll.currentTime = 0;
    sounds.gateRoll.play();
    
    setTimeout(function() {
      document.querySelector('#stargate').classList.remove('on');
      
      if (stargate.isOpen() == false) {
        sounds.fail.play();
        stargate.disableChevrons();
      }
    }, 20000);
  },
  
  stopMove: function() {
    document.querySelector('#stargate').classList.remove('on');
    if (!sounds.gateRoll.paused)
      sounds.gateRoll.pause()
  },
  
  // Activa un chevron aleatorio (o el "n" indicado)
  enableChevron: function(n = -1) {
    var arrowsOff = document.querySelectorAll('.arrow:not(.on)');
    if (n == -1)
      var n = Math.floor(Math.random() * arrowsOff.length);
    
    arrowsOff[n].classList.add('on');
    sounds.chevronClosed.play();
  },
  
  disableChevrons: function() {
    [].forEach.call(arrows, function(i) {
      i.classList.remove('on');
    });
  },
  
  enableWormHole: function() {
    this.CHAPAHAI_OPEN = true;
    this.stopMove();    
    sounds.gateOpen.play();
    document.getElementById('cartouche').style.display='block';
    document.querySelector('.wormhole').classList.add('on');
    setTimeout(function() { 
      sounds.wormHole.play();
    }, 3000);
    
    setTimeout(function() {
      stargate.disableWormHole();
    }, 18000);
  },
  
  disableWormHole: function() {
    this.CHAPAHAI_OPEN = false;
    sounds.gateClose.play();
    setTimeout(function() {
      document.querySelector('.wormhole').classList.remove('on');
      document.querySelector('.center-menu').classList.add('on');
      sounds.wormHole.pause();      
    }, 300);
    this.disableChevrons();
    //title.innerHTML = stargate.title;
  }
}