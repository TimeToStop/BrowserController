function getInnerByElementPath(path)
{
    return element_by_path(path).innerHTML;
}

function clickByElementID(path) 
{
    element_by_path(path).click();
}

function setTextToElement(path, text)
{
    element_by_path(path).value = text;
}
