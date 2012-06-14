package de.nigjo.nb.releaseview;

import javax.swing.SwingUtilities;

import org.openide.filesystems.FileChangeAdapter;
import org.openide.filesystems.FileEvent;

/**
 * Eine neue Klasse von hof. Erstellt am Jun 13, 2012, 4:16:37 PM
 *
 * @todo hier fehlt die Beschreibung der Klasse.
 *
 * @author hof
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
      return;
    executed = false;
    SwingUtilities.invokeLater(changer);
  }

}
