package de.nigjo.nb.releaseview;

import javax.swing.SwingUtilities;

import org.openide.filesystems.FileChangeAdapter;
import org.openide.filesystems.FileEvent;

/**
 * Manager to check for changes in a parent folder. Executes an update to the given
 * {@link FolderNodeList}.
 *
 * @author nigjo
 */
class FolderApperanceManager extends FileChangeAdapter
{
  private boolean executed;
  private final FolderNodeList list;
  private Runnable changer;

  FolderApperanceManager(FolderNodeList list)
  {
    this.list = list;
    changer = new Runnable()
    {
      @Override
      public void run()
      {
        FolderApperanceManager.this.list.update();
        executed = true;
      }

    };
    executed = true;
  }

  @Override
  public void fileFolderCreated(FileEvent fe)
  {
    update();
  }

  @Override
  public void fileDeleted(FileEvent fe)
  {
    update();
  }

  private void update()
  {
    if(!executed)
    {
      return;
    }
    executed = false;
    SwingUtilities.invokeLater(changer);
  }

}
