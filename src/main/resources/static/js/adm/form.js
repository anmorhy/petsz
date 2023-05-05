$(document).ready(function () {
    $(".alert").fadeTo(1, 1).removeClass('hidden');
    window.setTimeout(function () {
        $(".alert").fadeTo(1500, 0).slideUp(1200, function () {
            $(".alert").addClass('hidden');
        });
    }, 1000);
});

$(document).ready(function() {
	
	$('#Salvar').attr("disabled", "disabled");


	$("#inputNome").keyup(function(event){
		if($(this).val() == " " && $(this).val() == 0 ){
			$(this).val("");
		}
	});
	$('#inputNome').keyup( function (event) {
    	validarinput();
    });

    $("#inputResp").keyup(function(event){
		if($(this).val() == " " && $(this).val() == 0 ){
			$(this).val("");
		}
    });
	$('#inputResp').keyup( function (event) {
    	validarinput();	
    });

	$("#inputRegis").keyup(function(event){
		// if($(this).val() == " " && $(this).val() == 0 ){
		// 	$(this).val("");
		// }
    });
	$('#inputRegis').keyup( function (event) {
    	validarinput();	
    });

    $("#inputFone").keyup(function(event){
		if($(this).val() == " " && $(this).val() == 0 ){
			$(this).val("");
		}
	});
	$('#inputFone').keyup( function (event) {
    	validarinput();
    });

	$("#inputLogin").keyup(function(event){
		if($(this).val() == " " && $(this).val() == 0){
			$(this).val("");
		}
	});
	$('#inputLogin').keyup( function (event) {
    	validarinput();
    });
	

	function validarinput(){
		if($("#inputNome").val()==""|| $("#inputResp").val()=="" 
		|| $("#inputRegis").val()=="" || $("#inputFone").val()==""
        || $("#inputLocal").val()=="" || $("#inputSenha").val()=="" 
        ){

		$('#Salvar').attr("disabled", "disabled");
		}else{
			$('#Salvar').removeAttr("disabled");
		}
	}

});

function mascara(o,f){
    v_obj=o
    v_fun=f
    setTimeout("execmascara()",1)
}
function execmascara(){
    v_obj.value=v_fun(v_obj.value)
}  
function mtel(v){
    v=v.replace(/\D/g,""); //Remove tudo o que não é dígito
    v=v.replace(/^(\d{2})(\d)/g,"($1) $2"); //Coloca parênteses em volta dos dois primeiros dígitos
    v=v.replace(/(\d)(\d{4})$/,"$1-$2"); //Coloca hífen entre o quarto e o quinto dígitos
    return v;
}
function soDigito(v){
	v=v.replace(/\D/g,""); //Remove tudo o que não é dígito
	return v;
}
function id( el ){
	return document.getElementById( el );
}
window.onload = function(){
	id('inputFone').onkeyup = function(){
		mascara( this, mtel );
	}
	id('inputRegis').onkeyup = function(){
		mascara(this, soDigito);
	}
	

}