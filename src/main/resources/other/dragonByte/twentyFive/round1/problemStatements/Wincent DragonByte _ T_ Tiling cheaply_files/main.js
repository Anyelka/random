document.addEventListener('DOMContentLoaded', () => {
    // Select only the <pre> tags inside a <table class="io">
    const preTags = document.querySelectorAll('table.io pre');

    preTags.forEach(preTag => {
        // Create a wrapper and move the pre tag inside it
        const wrapper = document.createElement('div');
        wrapper.className = 'code-block-wrapper';
        preTag.parentNode.insertBefore(wrapper, preTag);
        wrapper.appendChild(preTag);

        // Create the copy button and append it to the wrapper
        const copyButton = document.createElement('button');
        copyButton.className = 'copy-code-button';
        copyButton.textContent = 'Copy';
        wrapper.appendChild(copyButton);

        // Add the click event listener to the button
        copyButton.addEventListener('click', () => {
            // The preTag is still available in this scope
            const code = preTag.querySelector('code');
            const textToCopy = code ? code.innerText : preTag.innerText;

            navigator.clipboard.writeText(textToCopy).then(() => {
                copyButton.textContent = 'Copied!';
                setTimeout(() => {
                    copyButton.textContent = 'Copy';
                }, 2000);
            }).catch(err => {
                console.error('Failed to copy: ', err);
            });
        });
    });
});
