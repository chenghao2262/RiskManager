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
    getInList(id);
}

function getInList(pid){
    var list = planList.list;
    var html = '';
    var inRidList = new Array();
    $.each(list, function(i){
        if(list[i].pid==pid){
            var inList = list[i].riskList;
            $.each(inList, function(j){
                inRidList.push(inList[j].rid);
                html += '<div class="row my-item" onClick="javascript:riskOut(this,'+inList[j].rid+');">'+
                    '<div class="col-sm-2">'+(j+1)+'</div>'+'' +
                    '<div class="col-sm-8">'+inList[j].riskTitle+'</div>'+
                    '</div>';
            });
        }

    });
    $("#in-list").html(html);
    updateOut(inRidList);
}
function updateOut(inRidList){
    var htmlObject = $.ajax({url: "mockList.txt", async: false});
    var all = JSON.parse(htmlObject.responseText).list;
    var outHtml = '';
    var outIndex = 1;
    $.each(all, function(i){
        var isIn = false;
        for(var j=0;j<inRidList.length;j++){
            if(inRidList[j]==all[i].rid){
                isIn = true;
                break;
            }
        }
        if(!isIn){
            outHtml += '<div class="row my-item" onClick="javascript:riskIn(this,'+all[i].rid+');">'+
                '<div class="col-sm-2">'+outIndex+'</div>'+'' +
                '<div class="col-sm-8">'+all[i].riskTitle+'</div>'+
                '</div>';
            outIndex++;
        }
    })
}

//将风险移出计划
function riskOut(self, rid){
    //var originHtml = document.getElementById("out-list").innerHTML;
    //$("#out-list").html(originHtml+self.outerHTML);
    //$(self).attr("onClick", "riskIn(this")
    //self.outerHTML = '';
    $.ajax({url:"#", async:false, data:"pid="+pidSelected+"&rid="+rid, type:"post"});
    getInList(pidSelected);
}

//将风险移入计划
function riskIn(self, rid){

    $.ajax({url:"#", async:false, data:"pid="+pidSelected+"&rid="+rid, type:"post"});
    getInList(pidSelected);
}
function getOutList(pid){

}

function create(){
    var name = $("#new-plan-name").val();

    $.ajax({url:"#", async:false, data:"name="+name, type:"post"});
    getPlanList();
}

$(document).ready(getPlanList);