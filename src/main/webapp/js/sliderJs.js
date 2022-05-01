
const slide = {
		slideIndex : 0,
		slideElements : document.querySelectorAll('.visual_img > li > img'),
		totalSlides : document.querySelectorAll('.visual_img > li').length,
		sliderWidth : document.querySelector('.container_visual').clientWidth,
		slider : document.querySelector('.visual_img'),
		
		setSliderWidth : function() {
			this.slideElements.forEach(function (element) {
			    element.style.width = slide.sliderWidth + 'px';
			})
		},
			
		setTotalSliderWidth : function() {
				this.slider.style.width = this.sliderWidth * this.totalSlides + 'px';
		},
		
		moveSlide : function() {
			this.slideIndex++;
			this.showSlides(this.slideIndex);
		},
		
		showSlides: function(slideIndex) {
			if (slideIndex == -1) {
		        this.slideIndex = this.totalSlides - 1;
		    } else if (slideIndex === this.totalSlides) {
		        this.slideIndex = 0;
		    }
		    this.slider.style.transform = "translateX("+ -(this.sliderWidth * this.slideIndex) + "px)";
		},
		
		intervalSlide: function() {
			setInterval(function () { slide.moveSlide(); }, 3000);
		},
		
		init: function() {
			this.setSliderWidth();
			this.setTotalSliderWidth();
			this.intervalSlide();
		}
	}