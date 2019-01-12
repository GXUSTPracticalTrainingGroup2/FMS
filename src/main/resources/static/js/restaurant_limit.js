    $(".save").click(function(){
        //把modal隐藏
        $('#mymodal').modal('hide');
    });
    //开启modal
    $('.smodal_1').click(function(){
        $('#mymodal_1').modal('show');
    });

    //    我的权限
    function funcShow(id){
        for(var i=0;i<5;i++){
            var divInfo =document.getElementById('div'+(i+1));
            divInfo.style.display='none';
        }
        var div =document.getElementById('div'+id);
        div.style.display='block';
    }

    //商家权限
    // 输入整数
    function isInt(str){
        var re=/^\d+/;
        return re.test(str);
    }
    // 输入日期格式
    function isData(str){
        var re=/^[1-2]\d{3}(-|\/)((0?[1-9]|1[0-2])(-|\/)(0?[1-9]|[1-2]\d))|((0?[13-9]|1[0-2])(-|\/)30)|((0?[13578]|1[02])(-|\/)31)$/;
        return re.test(str);
    }
    //不能为空
    function isBlank(str){
        var re=/^\s*$/;
        return re.test(str);
    }


    function YanZheng(id){
        return document.getElementById(id);
    }
    function chkInt(){
        if(!isBlank(YanZheng("idInt").value)){
            if(!isInt(YanZheng("idInt").value)){
                YanZheng("idIntPrompt").style.color="red";
                YanZheng("idIntPrompt").innerText="数量应为整数！";
                return false;
            }
        }
        YanZheng("idIntPrompt").style.color="black";
        YanZheng("idIntPrompt").innerText="";
        return true;
    }
    function chkSCData(){
        if(!isBlank(YanZheng("idSCData").value)){
            if(!isData(YanZheng("idSCData").value)){
                YanZheng("idSCDataPrompt").style.color="red";
                YanZheng("idSCDataPrompt").innerText="请输入日期格式！";
                return false;
            }
        }
        YanZheng("idSCDataPrompt").style.color="black";
        YanZheng("idSCDataPrompt").innerText="";
        return true;
    }
    function chkDQData(){
        if(!isBlank(YanZheng("idDQData").value)){
            if(!isData(YanZheng("idDQData").value)){
                YanZheng("idDQDataPrompt").style.color="red";
                YanZheng("idDQDataPrompt").innerText="请输入日期格式！";
                return false;
            }
        }
        YanZheng("idDQDataPrompt").style.color="black";
        YanZheng("idDQDataPrompt").innerText="";
        return true;
    }