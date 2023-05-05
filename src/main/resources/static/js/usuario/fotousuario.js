$(document).ready(function () {
	buscarfoto();
});
function buscarfoto() {
	var url = '/use/buscarfoto';
	console.log(url);
	$.ajax({
		type: 'GET',
		url: url
	}).done(function (data) {
		//console.log(data);
		if (data == null || data == "") {
			$("#fotoUser").attr('src', "/img/profile-1.png");
		} 


	}).fail(function (jqXHR, textStatus, errorThrown) {
	});
}

$(document).ready(function () {
	$('#fileImage').change(function () {
		showImageThumbnail(this);
		$("#fotos").removeAttr("hidden");
	});
});


function showImageThumbnail(fileInput) {
	file = fileInput.files[0];
	reader = new FileReader();

	reader.onload = function (e) {
		$('#thumbnail').attr('src', e.target.result);
	};

	reader.readAsDataURL(file);
}

$("#uploader").change(function () {
	if (this.files && this.files[0]) {
		var reader = new FileReader();

		reader.onload = function (e) {
			$("#thumbnail").attr("src", e.target.result);
		};

		reader.readAsDataURL(this.files[0]);
	}
});

var upload = document.getElementById("fileImage");
upload.addEventListener("change", function (e) {
	var size = upload.files[0].size;
	if (size < 5000000) { //1MB         
		//      alert('Permitido'); //Abaixo do permitido
	} else {
		alert('Tamanho limite 5MB'); //Acima do limite
		upload.value = ""; //Limpa o campo          
	}
	e.preventDefault();
});