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

package net.dv8tion.jda.internal.entities;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.internal.utils.Helpers;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.FormattableFlags;
import java.util.Formatter;

public abstract class AbstractMessage implements Message
{
    protected final String content;
    protected final String nonce;
    protected final boolean isTTS;

    public AbstractMessage(String content, String nonce, boolean isTTS)
    {
        this.content = content;
        this.nonce = nonce;
        this.isTTS = isTTS;
    }

    @Nonnull
    @Override
    public String getContentRaw()
    {
        return content;
    }

    @Override
    public String getNonce()
    {
        return nonce;
    }

    @Override
    public boolean isTTS()
    {
        return isTTS;
    }

    protected abstract void unsupported();

    @Override
    public void formatTo(Formatter formatter, int flags, int width, int precision)
    {
        boolean upper = (flags & FormattableFlags.UPPERCASE) == FormattableFlags.UPPERCASE;
        boolean leftJustified = (flags & FormattableFlags.LEFT_JUSTIFY) == FormattableFlags.LEFT_JUSTIFY;

        String out = content;

        if (upper)
            out = out.toUpperCase(formatter.locale());

        appendFormat(formatter, width, precision, leftJustified, out);
    }

    protected void appendFormat(Formatter formatter, int width, int precision, boolean leftJustified, String out)
    {
        try
        {
            Appendable appendable = formatter.out();
            if (precision > -1 && out.length() > precision)
            {
                appendable.append(Helpers.truncate(out, precision - 3)).append("...");
                return;
            }

            if (leftJustified)
                appendable.append(Helpers.rightPad(out, width));
            else
                appendable.append(Helpers.leftPad(out, width));
        }
        catch (IOException e)
        {
            throw new UncheckedIOException(e);
        }
    }
}
