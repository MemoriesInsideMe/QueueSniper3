package me.vrekt.queuesniper.command.commands;

import me.vrekt.queuesniper.command.Command;
import me.vrekt.queuesniper.guild.GuildConfiguration;
import me.vrekt.queuesniper.message.MessageAction;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.exceptions.InsufficientPermissionException;

import java.util.List;

public class UnlockCommand extends Command {

    public UnlockCommand() {
        super("unlock", new String[]{}, true);
    }

    @Override
    public void execute(List<String> arguments, Member from, Message message, TextChannel channel, GuildConfiguration configuration) {
        try {
            channel.putPermissionOverride(configuration.getGuild().getPublicRole()).setAllow(Permission.MESSAGE_WRITE).queue();
            configuration.getAnnouncers().forEach(role -> channel.putPermissionOverride(role).setAllow(Permission.MESSAGE_WRITE).queue());
            MessageAction.send(channel, "*Channel unlocked..*");
        } catch (InsufficientPermissionException exception) {
            MessageAction.send(channel, from.getAsMention() + " I do not have permission to do this.");
        }
    }
}
