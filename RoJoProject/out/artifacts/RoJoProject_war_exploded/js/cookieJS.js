/**
 * Created by taoyali on 2018/2/28.
 */
function getCookie(name)
{
    var cookieValue = "";
    var search = escape(name) + "=";
    if(document.cookie.length > 0)
    {
        offset = document.cookie.indexOf(search);
        if (offset != -1)
        {
            offset += search.length;
            end = document.cookie.indexOf(";", offset);
            if (end == -1)
                end = document.cookie.length;
            cookieValue = decodeURIComponent((document.cookie.substring(offset, end)))
        }
    }
    return cookieValue;
}