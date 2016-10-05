package de.nigjo.nb.releaseview;

import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ui.support.NodeFactory;
import org.netbeans.spi.project.ui.support.NodeList;

/**
 * Basic Factory to create a Node for the {@code release} folder in NetBeans RCP Modules.
 *
 * @author nigjo
 */
@NodeFactory.Registration(projectType =
    "org-netbeans-modules-apisupport-project",
    position = 168)
public class ReleaseFilesViewerFactory implements NodeFactory
{
  @Override
  public NodeList<?> createNodes(Project p)
  {
    FolderNodeList folderNodeList = new FolderNodeList(p, "release");
    p.getProjectDirectory().addFileChangeListener(
        new FolderApperanceManager(folderNodeList));

    return folderNodeList;

  }

}
