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

package net.dv8tion.jda.api.events.self;

import net.dv8tion.jda.annotations.DeprecatedSince;
import net.dv8tion.jda.annotations.ForRemoval;
import net.dv8tion.jda.api.JDA;

import javax.annotation.Nonnull;

/**
 * Indicates that the email of the current user changed. (client-only)
 *
 * <p>Can be used to retrieve the old email.
 *
 * <p>Identifier: {@code email}
 *
 * @deprecated This is no longer supported
 */
@Deprecated
@ForRemoval
@DeprecatedSince("4.2.0")
public class SelfUpdateEmailEvent extends GenericSelfUpdateEvent<String>
{
    public static final String EVENT_TYPE = "email";

    public SelfUpdateEmailEvent(@Nonnull JDA api, long responseNumber, @Nonnull String oldEmail)
    {
        super(api, responseNumber, oldEmail, api.getSelfUser().getEmail(), EVENT_TYPE);
    }

    /**
     * The old email
     *
     * @return The old email
     */
    @Nonnull
    public String getOldEmail()
    {
        return getOldValue();
    }

    /**
     * The new email
     *
     * @return The new email
     */
    @Nonnull
    public String getNewEmail()
    {
        return getNewValue();
    }

    @Nonnull
    @Override
    public String getOldValue()
    {
        return super.getOldValue();
    }

    @Nonnull
    @Override
    public String getNewValue()
    {
        return super.getNewValue();
    }
}
