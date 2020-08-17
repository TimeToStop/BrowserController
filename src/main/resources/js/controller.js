function getInnerByElementPath(path)
{
    return element_by_path(path).innerHTML;
}

function clickByElementID(path) 
{
    element_by_path(path).click();
}

function sendTextInput(path, text)
{
    $(element_by_path(path)).val(text);
}

function exists(path)
{
    return element_by_path(path) !== null;
}