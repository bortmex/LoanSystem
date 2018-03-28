function date_time(id)
{
    date = new Date;
    h = date.getHours();
    if(h<10) h = "0"+h;
    m = date.getMinutes();
    if(m<10) m = "0"+m;
    s = date.getSeconds();
    if(s<10) s = "0"+s;
    result = ''+h+':'+m+':'+s;
    document.getElementById(id).innerHTML = result;
    setTimeout('date_time("'+id+'");','1000');
    return true;
}