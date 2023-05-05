$(document).ready(function () {
  $(".alert").fadeTo(1, 1).removeClass('hidden');
  window.setTimeout(function () {
      $(".alert").fadeTo(1500, 0).slideUp(1200, function () {
          $(".alert").addClass('hidden');
      });
  }, 1000);
});

