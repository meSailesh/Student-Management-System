$(document).ready( function () {
    $('.subject-table #deleteButton').one('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delRef').attr('href', href);

       $('#deleteModal').modal();
    });
} );