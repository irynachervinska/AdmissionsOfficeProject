var selItem = localStorage.getItem("lang");
$('#locates').val(selItem ? selItem : 'en');
$("#locates").change(function () {
    var selectedLang = $('#locates').val();
    if (selectedLang) {
        localStorage.setItem("lang", selectedLang);
        window.location.replace('?lang=' + selectedLang);
    }
});