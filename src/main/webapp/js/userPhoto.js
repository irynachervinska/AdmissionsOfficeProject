$("#photo").change(function () {
    var formData = new FormData();
    var photo = $('#photo')[0].files[0];
    var name = $("#token").attr('name');
    var value = $("#token").val();

    if (photo === undefined) {
        return;
    }

    formData.append('userPhoto', photo);
    $.ajax({
        url: '/user-photo/upload?' + name + '=' + value,
        type: 'post',
        data: formData,
        contentType: false,
        processData: false,
        success: function (userPhotoId) {
            $("#userPhoto")
                .attr("src", "/user-photo/download/" + userPhotoId);
            $("#photo-id").val(userPhotoId)
        },
    });
});