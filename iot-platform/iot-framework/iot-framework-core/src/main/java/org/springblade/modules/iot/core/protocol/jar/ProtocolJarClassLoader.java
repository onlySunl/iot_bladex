

package org.springblade.modules.iot.core.protocol.jar;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 协议jar类加载器
 *
 * @author gitee.com/NexIoT
 * @version 1.0
 * @since 2025/8/9 19:23
 */
public class ProtocolJarClassLoader extends URLClassLoader {

  private final URL[] urls;

  public ProtocolJarClassLoader(URL[] urls, ClassLoader parent) {
    super(urls, parent);
    this.urls = urls;
  }

  @Override
  public void close() throws IOException {
    super.close();
  }

  public URL[] getUrls() {
    return this.urls;
  }
}
