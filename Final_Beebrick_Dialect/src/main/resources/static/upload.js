upload = {
		/**
		 * Hiển thị hành ảnh khi chọn ảnh
		 * @param file trường file
		 * @param img selector của hình 
		 */
		change(file, img){
			if(file.files.length > 0){
				var reader = new FileReader();
				reader.onload = function(){
					$(img).attr("src", reader.result);
				};
				reader.readAsDataURL(file.files[0]);				
			}
		}
}