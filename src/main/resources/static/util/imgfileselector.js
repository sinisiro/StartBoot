/*
 * 파일 셀렉터 라이브러리
 * 기존 multifile.js 수정 
 * by 안정진
 * 최종 수정일 2018.02.13
*/

	
function imgFileSelector(list_target, maxNum , maxSize) {

	// Where to write the list
	this.list_target = list_target;
	// How many elements?
	this.count = 0;
	// How many elements?
	this.id = 0;
	// Is there a maximum?
	if (maxNum) {
		this.maxNum = maxNum;
	} else {
		this.maxNum = -1;
	}
	;
	if (maxSize) {
		this.maxSize = maxSize;
	} else {
		this.maxSize = -1;
	}
	;
	// 시퀀스
	this.seqNo = 0;

	//this.imageArray = new Array();
	this.imageMap = new Map();
	
	/**
	 * Add a new file input element
	 */
	this.init = function(element) {
	    console.log("init");

		// Make sure it's a file input element
		if (element.tagName == 'INPUT' && element.type == 'file') {

			// Element name -- what number am I?
			element.name = 'file_' + this.id++;

			element.id = 'upload-image';
			element.multiple='multiple';
			element.accept='image/*';
			// Add reference to this object
			element.imgfile_selector = this;

			// What to do when a file is selected
			element.onchange = function() {
				
				var fileslength = this.files.length;
				var n;
				for(n=0;n<fileslength;n++)
				{
					var filename = this.files[n].name;
					var fileExt = filename.substring(filename.lastIndexOf('.')+1).toLowerCase();
					
					if(fileExt != 'gif'
						&& fileExt != 'png'
						&& fileExt != 'jpg'
						&& fileExt != 'jpeg'
						&& fileExt != 'tiff'
						&& fileExt != 'tif'
						&& fileExt != 'bmp'	)
					{
						alert("이미지만 전송가능합니다.");
						this.value = "";				
						return;
					}

				}

				if (this.imgfile_selector.imageMap.size + this.files.length > this.imgfile_selector.maxNum)
				{
					alert(this.imgfile_selector.maxNum + "장까지 가능합니다.");
					this.value = "";
					return;
				}
				else
				{
					showThumbnail(this.files, this);
					this.value = "";
				}

			};
		} 
		else 
		{
			// This can only be applied to file input elements!
			alert('Error: not a file input element');
		}
		;
	};

	function showThumbnail(files, element) 
	{
		for (var i = 0; i < files.length; i++) 
		{
			var seqNo = element.imgfile_selector.seqNo++;
			
			var file = files[i];
			var imageType = /image.*/;

			if (!file.type.match(imageType)) {
				console.log("not an Image");
				continue;
			}

			var div = document.createElement("div");
			div.setAttribute("name", 'divthumbimg_' + seqNo);
			
			var image = document.createElement("img");
			image.seqNo = seqNo;
			
			var checkbox = document.createElement("input");
			checkbox.type = 'checkbox';
			checkbox.setAttribute("name", 'filecheckbox_' + seqNo);
			checkbox.value = seqNo; 
			checkbox.style.display = "none";

			var thumbnail = document.getElementById("thumbnail");

			div.appendChild(checkbox);
			
			div.appendChild(image);
			thumbnail.appendChild(div);
			
			var filename = file.name;
			var fileExt = filename.substring(filename.lastIndexOf('.')+1).toLowerCase();
			
			console.log("filename : "  + filename);
			console.log("fileExt : "  + fileExt);
			
			//var tempImage = document.createElement("img");			
			var reader = new FileReader();
			
			// Closure to capture the file information.
			reader.onload = 
			(	
				function(aImg,seqNo,imageMap,maxSize,fileExt) 
				{
					return function(e) 
				    {
						console.log("reader.onload : "  + seqNo + "-" + e.target.result.length);				
					    aImg.src = e.target.result;	
					    
					    if(fileExt == "tiff" || fileExt == "tif")
					    {
					    	imageMap.set(seqNo, aImg.src);
					    }
					    else
					    	resizedataURL(e.target.result,maxSize,seqNo,imageMap);
				     };
				}(image,seqNo,element.imgfile_selector.imageMap,element.imgfile_selector.maxSize,fileExt)
			);
			var ret = reader.readAsDataURL(file);
		}
	}
	
	// Takes a data URI and returns the Data URI corresponding to the resized image at the wanted size.
	function resizedataURL(datas, maxSize,seqNo, imageMap)
    {
        // We create an image to receive the Data URI
        var img = document.createElement('img');

        // When the event "onload" is triggered we can resize the image.
        img.onload = function()
        {        
            var originalWidth  = img.naturalWidth;
			var originalHeight = img.naturalHeight;
			
			console.log("image width : "  + originalWidth + ", height : " + originalHeight);
			
			var imageSize = img.src.length;
			
			console.log("image size : "  + imageSize);
			
			if(maxSize > 0 && imageSize > maxSize)
			{

				var ratio = Math.sqrt(maxSize / (originalWidth*originalHeight));
				
				console.log("ratio first : "  + ratio);
				
				for(var n=0;n<10;n++)
				{
					if(n>0)
						ratio = ratio * 0.5 ;
					
					var wantedWidth = parseInt(originalWidth * ratio);
					var wantedHeight = parseInt(originalHeight * ratio);
					
					console.log("image resize Yes, wantedWidth : "  + wantedWidth 
							+ ",wantedHeight : " + wantedHeight);
					
					var canvas = document.createElement('canvas');
		            var ctx = canvas.getContext('2d');
		            
		            // We set the dimensions at the wanted size.
		            canvas.width  = wantedWidth;
		            canvas.height = wantedHeight;
					
		            // We resize the image with the canvas method drawImage();
		            ctx.drawImage(this, 0, 0, originalWidth,originalHeight,0,0, wantedWidth, wantedHeight);

		            var dataURI = canvas.toDataURL();
		         
		            console.log("dataURI.length : "  + dataURI.length);  
		            
		            if(dataURI.length < maxSize)
		            {
		            	imageMap.set(seqNo, dataURI);
		            	break;
		            }
				}
			}
			else
			{
				console.log("image resize No");
				imageMap.set(seqNo, img.src);
			}
				
			console.log("imageMap.length : "  + imageMap.size);
        };

        // We put the Data URI in the image's src attribute
        img.src = datas;
    }
};