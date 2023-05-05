$(document).ready(function () {
    $(".alert").fadeTo(1, 1).removeClass('hidden');
    window.setTimeout(function () {
        $(".alert").fadeTo(1500, 0).slideUp(1200, function () {
            $(".alert").addClass('hidden');
        });
    }, 1000);
});
// $().alert('close')

$('.table #remover').click(function (event){
  event.preventDefault();
  var href = $(this).attr('href');
  $('#exampleModal #btnRemover').attr('href', href);
  $('#exampleModal').modal();
});

$(document).ready(function() {
    //$('#example').DataTable();
    $('#example').DataTable( {
        "language": {
            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Portuguese-Brasil.json"
        }
    } );
} );