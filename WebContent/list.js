var json;
var rid;
function refreshList() {
    var htmlObject = $.ajax({url: "getRisks", async: false});
    json = JSON.parse(htmlObject.responseText);

    var listHtml =
        makeItemHtml(json);
    ;

    $('#list-panel').html(listHtml
    )
}

//function ajaxRespond(response){
//    //
//}
function select(index) {

}

//$('p-list').button();
//$('.p-btn').on('click', function(){
//    alert("hh")
//});
//$('#p-low').on('click', function(){
//    alert("hh");
//});
var possibilityId;
function pClick(id) {
    //var id = s.id;
    //alert($(self).id);
    $('#p-l-low').removeClass('active');
    $('#p-l-high').removeClass('active');
    $('#p-l-middle').removeClass('active');
    $('#' + id).addClass('active');
    possibilityId = id;
    //alert(possibilityId)
}

var influenceId;
function iClick(id) {
    //var id = s.id;
    //alert(id);
    $('#i-l-low').removeClass('active');
    $('#i-l-high').removeClass('active');
    $('#i-l-middle').removeClass('active');
    $('#' + id).addClass('active');
    influenceId = id;
    //alert(possibilityId)
}

function mockRespond(response) {
    response = new JSON
}

function makeItemHtml(json) {
    var html = '';
    var list = json.list;
    $.each(list, function (i) {
        html +=
            '<div class="row list-item" id="item' + i + '" onClick="itemClick(this.id,' + i + ');">'
            + '<div class="col-sm-2">' + (i + 1) + '</div>'
            + '<div class="col-sm-6">' + list[i].riskTitle + '</div>'
            + '<div class="col-sm-2">可能性：' + list[i].riskPossibility + '</div>'
            + '<div class="col-sm-2">严重度：' + list[i].riskInfluence + '</div>'
            + '</div>';
    });
    return html;
}

function itemClick(id, index) {
    //alert(id+"\t"+index);
    var item = json.list[index];

    rid = item.rid;
    var title = item.riskTitle;
    var possibility = item.riskPossibility;
    var influence = item.riskInfluence;
    var content = item.content;
    var threshold = item.threshold;
    var creator = item.creator;
    var trackers = item.tracker;

    updateDetail(title, possibility, influence, threshold, content, creator, trackers);

}

function updateDetail(title, possibility, influence, threshold, content, creator, trackers) {
    $('#detail-title').val(title);
    $('#detail-content').val(content);
    $('#detail-threshold').val(threshold);
    //$('#detail-title').val(title);
    //$('#detail-title').val(title);
    document.getElementById('creator').innerHTML = '创建者：' + trackers[0];
    var trackerStr = "";
    $.each(trackers, function (i) {
        trackerStr += "<badge>"+trackers[i].userid + "</badge> ";
    });
    document.getElementById('tracker').innerHTML = '跟踪者：' + trackerStr;

    if (possibility == '高') {
        pClick('p-l-high');
    } else if (possibility == '中') {
        pClick('p-l-middle');
    } else if (possibility == '低') {
        pClick('p-l-low');
    }

    if (influence == '高') {
        iClick('i-l-high');
    } else if (influence == '中') {
        iClick('i-l-middle');
    } else if (influence == '低') {
        iClick('i-l-low');
    }
}

function save() {
    alert("save");
    var title = $("#detail-title").val();
    var content = $("#detail-content").val();
    var threshold = $("#detail-threshold").val();
    var newTracker = $("#detail-new-tracker").val();
    var influence = "";
    if (influenceId == "i-l-high") {
        influence = "高";
    } else if (influenceId == "i-l-middle") {
        influence = "中";
    } else {
        influence = "低";
    }

    var possibility = "";
    if (possibilityId == "p-l-high") {
        possibility = "高";
    } else if (possibilityId == "p-l-middle") {
        possibility = "中";
    } else {
        possibility = "低";
    }
    //alert(title+","+content+","+threshold+"影响："+influence+" 可能："+possibility);
    var html = $.ajax({
        type: "post",
        url: "modify",
        data: "rid="+rid+"&riskTitle=" + title + "&riskPossibility=" + possibility + "&riskInfluence=" + influence + "&content=" + content
        + "&threshold=" + threshold+"&newTracker="+newTracker,
        async: false
    }).responseText;
    refreshList();
}

function create() {
    rid = -1;
    updateDetail('', '低', '低', '', '', '', {});
}

function del() {
    //alert(rid);
    var html = $.ajax({
        type: "post",
        url: "del",
        data: "rid=" + rid,
        async: false
    }).responseText;
    refreshList();
}

function getUserList(){
    var html = $.ajax({
        type: "post",
        url: "modify",
        async: false
    }).responseText;

}

function setAuthentic(a){

}

$(document).ready(refreshList);