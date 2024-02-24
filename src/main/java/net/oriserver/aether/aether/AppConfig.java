package net.oriserver.aether.aether;

import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "net.oriserver.aether.aether")
public class AppConfig {
    @Autowired JavaPlugin javaPlugin;
}
