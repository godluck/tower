function headRotate () {
	// body...
}

function Rotate(img){
	this.init(img);
}
Rotate.prototype = {
	constructor : Rotate,
	init : function(img){
		this.img = img;
			this.initWidth = this.img.width;
			this.initHeight = this.img.height;			
		if(!document.all || window.opera){
			var canvas = document.createElement("canvas");
			this.ctx = canvas.getContext('2d');
			canvas.setAttribute("width", this.img.width);
			canvas.setAttribute("height", this.img.height);		
			this.ctx.drawImage(this.img,0,0);
			this.ghost = this.img;
			this.img.parentNode.replaceChild(canvas,this.img);
			this.img = canvas;
		}
		
		this.direction = 0;
	},
	run : function(){					
		if(document.all && !window.opera){
			this.img.style.filter = 'progid:DXImageTransform.Microsoft.BasicImage(Rotation=' + this.direction + ')';
			var rate = this.initWidth / this.initHeight;
			if(this.direction % 2 != 0){
				this.img.width = Math.min(rate * this.initWidth , this.initWidth);
				this.img.height = this.initWidth;
			}else{
				this.img.width = this.initWidth;
				this.img.height = this.initHeight;					
			}	
		}else{
			var w = h = dx = dy = a = 0;
			switch (this.direction){
				case 0:
					w = this.ghost.width;
					h = this.ghost.height;	
					dx = 0;
					dy = 0;								
					a = 0;
					break;
				case 1:
					w = this.ghost.height;
					h = this.ghost.width;
					dx = 0;
					dy = - this.ghost.height;								
					a = 90 * Math.PI / 180;				
					break;
				case 2:
					w = this.ghost.width;
					h = this.ghost.height;
					dx = - this.ghost.width;
					dy = - this.ghost.height;
					a = 180 * Math.PI / 180;					
					break;
				case 3:
					w = this.ghost.height;
					h = this.ghost.width;
					dx = - this.ghost.width;
					dy = 0;			
					a = 270 * Math.PI / 180;					
					break;
			}	
			console.log(this.direction);	
			this.img.setAttribute('width', w);
			this.img.setAttribute('height', h);
			this.ctx.rotate(a);
			this.ctx.drawImage(this.ghost, dx, dy);	
			if(this.direction === 1 || this.direction === 3 ){
				this.img.style.width = Math.min(this.ghost.width, this.initHeight) + "px";
			}
		}
	},
	runLeft : function(){	
		this.direction--;
		if(this.direction < 0){
			this.direction = 3;
		}
		
		if(this.direction >= 4){
			this.direction = 0;
		}
		this.run();
		
	},
	runRight : function(){	
		this.direction++;	
		if(this.direction < 0){
			this.direction = 3;
		}
		
		if(this.direction > 3){
			this.direction = 0;
		}
		this.run();
			
	}
};
function init(divs){
	for(var i = 0, len = divs.length; i < len; i++){
		(function(o){
			var left = o.getElementsByTagName("input")[0],
				right = o.getElementsByTagName("input")[1],
				img = o.getElementsByTagName("img")[0],
				rotate = new Rotate(img);
			left.onclick = function(){
				rotate.runLeft();
			};
			right.onclick = function(){
				rotate.runRight();
			}			
		})(divs[i]);
	}
}
window.onload = function(){
	init(document.getElementsByTagName("div"));
}