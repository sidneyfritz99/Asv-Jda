/*
 * Copyright 2015-2020 Austin Keener, Michael Ritter, Florian Spieß, and the JDA contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.dv8tion.jda.api.events.channel.text.update;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.annotation.Nonnull;

/**
 * Indicates that a {@link net.dv8tion.jda.api.entities.TextChannel TextChannel}'s position changed.
 *
 * <p>Can be used to detect when a TextChannel position changes and get its previous position.
 *
 * <p>Identifier: {@code position}
 */
public class TextChannelUpdatePositionEvent extends GenericTextChannelUpdateEvent<Integer>
{
    public static final String EVENT_TYPE = "position";

    public TextChannelUpdatePositionEvent(@Nonnull JDA api, long responseNumber, @Nonnull TextChannel channel, int oldPosition)
    {
        super(api, responseNumber, channel, oldPosition, channel.getPositionRaw(), EVENT_TYPE);
    }

    /**
     * The old position
     *
     * @return The old position
     */
    public int getOldPosition()
    {
        return getOldValue();
    }

    /**
     * The new position
     *
     * @return The new position
     */
    public int getNewPosition()
    {
        return getNewValue();
    }
}
