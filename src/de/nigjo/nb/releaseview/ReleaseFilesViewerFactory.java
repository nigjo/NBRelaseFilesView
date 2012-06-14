
package de.nigjo.nb.releaseview;

import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ui.support.NodeFactory;
import org.netbeans.spi.project.ui.support.NodeList;

/**
 * Eine neue Klasse von hof. Erstellt am Jun 13, 2012, 4:14:15 PM
 *
 * @todo hier fehlt die Beschreibung der Klasse.
 *
 * @author hof
 */
public class ReleaseFilesViewerFactory implements NodeFactory{

  @Override
  public NodeList<?> createNodes(Project p)
  {
    FolderNodeList folderNodeList = new FolderNodeList(p, "release");
    p.getProjectDirectory().addFileChangeListener(
        new FolderApperanceManager(folderNodeList));

    return folderNodeList;
    
  }

}
