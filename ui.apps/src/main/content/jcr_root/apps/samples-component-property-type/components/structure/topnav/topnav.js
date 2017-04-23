
// Server-side JavaScript for the topnav logic
use(function () {
    var items = [];
	
	// get starting point of navigation
    var absParent = properties.get('absParent', 1);
    var root = currentPage.getAbsoluteParent(absParent);
    
    if (log.isDebugEnabled()) {
    	log.debug('absParent = {}, root.getPath() = {}', absParent, root? root.getPath(): null);
    }

    //if not deep enough take current node
    if (!root) {root=currentPage;}

    var currentNavPath = currentPage.getAbsoluteParent(2).getPath();
    var it = root.listChildren(new Packages.com.day.cq.wcm.api.PageFilter());

    while (it.hasNext()) {
        var page = it.next();

        // No strict comparison, because the types returned from the Java APIs
        // don't strictly match the JavaScript types
        var selected = (page.getPath() == currentNavPath);

        items.push({
            page: page,
            selected : selected
        });
    }

    return {
        items: items
    };
});
