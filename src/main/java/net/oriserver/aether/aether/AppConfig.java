package net.oriserver.aether.aether;

import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "net.oriserver.aether.aether")//現パッケージ階層以下の@Componentを読み込み
public class AppConfig {//Spring Bootの管理クラス
    @Autowired JavaPlugin javaPlugin;
}
