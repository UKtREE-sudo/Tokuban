$("#ans1").text("");
    $("#ans2").text("");
    var json1 = {
      ans1: "question.firstOption",
      ans2: "question.secondOption",
    };
    $.ajax({
      url: "http://localhost:8765/test1/index",
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(json1),
      dataType: "json",
    })
      .done(function (data1, textStatus, jqXHR) {
        $("#ans1").text(jqXHR.status); //例：200
        $("#ans2").text(JSON.stringify(data1));
      })
      .fail(function (jqXHR, textStatus, errorThrown) {
        $("#ans1").text(jqXHR.status); //例：404
      })
      .always(function () {});