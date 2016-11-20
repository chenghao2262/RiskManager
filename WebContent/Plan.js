/**
 * Created by Ian on 2016/11/20.
 */
var pidSelected;
var planList;
function getPlanList(){
    var htmlObject = $.ajax({url: "planResponse.txt", async: false});
    planList = JSON.parse(htmlObject.responseText);

    var listHtml = makePlanItemHtml(planList.list);
//alert(htmlObject.responseText);

    $('#plan-list').html(listHtml)
}

function makePlanItemHtml(list){
    var html = '';
    $.each(list, function(i){

        html += '<div class="row my-item" onClick="javascript:planSelect('+list[i].pid+');">'+
            '<div class="col-sm-2">'+(i+1)+'</div>'+'' +
            '<div class="col-sm-10">'+list[i].name+'</div>'+
            '</div>';
    });
    return html;
}

function planSelect(id){
    pidSelected = id;
    getInList(pid);
}

function getInList(pid){
    inList = planList.list.riskList;
    var html = '';
    $.each(inList, function(i){

        html += '<div class="row my-item" onClick="javascript:planSelect('+list[i].pid+');">'+
            '<div class="col-sm-2">'+(i+1)+'</div>'+'' +
            '<div class="col-sm-10">'+list[i].name+'</div>'+
            '</div>';
    });
}

function getOutList(pid){

}

function create(){
    var name = $("#new-plan-name").val();
}

$(document).ready(getPlanList);