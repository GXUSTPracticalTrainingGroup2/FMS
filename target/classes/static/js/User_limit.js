//   我的资料
    $(".save").click(function(){
        alert('保存成功！');
        //把modal隐藏
        $('#mymodal').modal('hide');
    });
    //开启modal
    $('.smodal_1').click(function(){
        $('#mymodal_1').modal('show');
    });

//    我的权限
    function funcShow(id){
        for(var i=0;i<3;i++){
            var divInfo =document.getElementById('div'+(i+1));
            divInfo.style.display='none';
        }
        var div =document.getElementById('div'+id);
        div.style.display='block';
    }