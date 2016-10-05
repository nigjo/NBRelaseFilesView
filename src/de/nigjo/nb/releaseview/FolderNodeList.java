package de.nigjo.nb.releaseview;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import java.awt.Image;

import javax.swing.SwingUtilities;
import javax.swing.event.ChangeListener;

import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ui.support.NodeList;

import org.openide.filesystems.FileObject;
import org.openide.loaders.DataFolder;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.ChangeSupport;
import org.openide.util.ImageUtilities;

/**
 * Node provider for the "Release Files" Node.
 *
 * @author nigjo
 */
class FolderNodeList implements NodeList<FileObject>
{
  private final ChangeSupport cs;
  private final Project project;
  private final String foldername;
  private static final Image BADGE =
      ImageUtilities.loadImage("de/nigjo/nb/releaseview/floppy_badge.png");

  FolderNodeList(Project p, String foldername)
  {
    this.project = p;
    this.foldername = foldername;
    cs = new ChangeSupport(this);
  }

  @Override
  public List<FileObject> keys()
  {
    FileObject prjDir = project.getProjectDirectory();
    FileObject resDir = prjDir.getFileObject(foldername);
    if(resDir == null)
    {
      return Collections.emptyList();
    }
    return Arrays.asList(resDir);
  }

  @Override
  public void addChangeListener(ChangeListener l)
  {
    cs.addChangeListener(l);
  }

  @Override
  public void removeChangeListener(ChangeListener l)
  {
    cs.removeChangeListener(l);
  }

  @Override
  public Node node(FileObject key)
  {
    DataFolder folder = DataFolder.findFolder(key);
    Node folderNode = folder.getNodeDelegate();

    folderNode = new FilterNode(folderNode)
    {
      @Override
      public String getDisplayName()
      {
        return org.openide.util.NbBundle.getMessage(FolderNodeList.class,
            "FolderNodeList.node_title");
      }

      @Override
      public Image getIcon(int type)
      {
        Image org = super.getIcon(type);
        return ImageUtilities.mergeImages(org, BADGE, 6, 6);
      }

      @Override
      public Image getOpenedIcon(int type)
      {
        Image org = super.getOpenedIcon(type);
        return ImageUtilities.mergeImages(org, BADGE, 6, 6);
      }

    };

    return folderNode.cloneNode();
  }

  @Override
  public void addNotify()
  {
  }

  @Override
  public void removeNotify()
  {
  }

  void update()
  {
    if(SwingUtilities.isEventDispatchThread())
    {
      cs.fireChange();
    }
    else
    {
      SwingUtilities.invokeLater(new Runnable()
      {
        @Override
        public void run()
        {
          cs.fireChange();
        }

      });
    }
  }

}
