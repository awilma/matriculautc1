jQuery.validator.addMethod("lettersonly", function(value, element) {
		  //return this.optional(element) || /^[a-z]+$/i.test(value);
		  return this.optional(element) || /^[A-Za-zÁÉÍÑÓÚáé íñó]*$/.test(value);
		 
		}, "Letters only please"); 

jQuery.validator.addMethod("numbersonly", function(value, element) {
	  //return this.optional(element) || /^[a-z]+$/i.test(value);
	  return this.optional(element) || /[0-9]/.test(value);
	 
	}, "Numbers only please"); 
jQuery.validator.addMethod('filesize', function(value, element, param) {
    // param = size (en bytes) 
    // element = element to validate (<input>)
    // value = value of the element (file name)
    return this.optional(element) || (element.files[0].size <= param) 
});