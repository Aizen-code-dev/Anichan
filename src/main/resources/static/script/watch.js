window.addEventListener('DOMContentLoaded', () => {
    const episodes = document.querySelectorAll('.episode-item');
    const select = document.getElementById('episode-range');
    const rangeSize = 100;

    const totalEpisodes = Math.max(...Array.from(episodes).map(ep => parseInt(ep.getAttribute('data-episode-number'))));
    const totalRanges = Math.ceil(totalEpisodes / rangeSize);

    // Populate select options dynamically
    for (let i = 0; i < totalRanges; i++) {
      const start = i * rangeSize + 1;
      const end = Math.min((i + 1) * rangeSize, totalEpisodes);

      const option = document.createElement('option');
      option.value = `${start}-${end}`;
      option.textContent = `${start} - ${end}`;
      select.appendChild(option);
    }

    // Filter function
    function filterEpisodesByRange(start, end) {
      episodes.forEach(ep => {
        const number = parseInt(ep.getAttribute('data-episode-number'));
        ep.style.display = (number >= start && number <= end) ? 'flex' : 'none';
      });
    }

    // Listen to select change
    select.addEventListener('change', function () {
      const [start, end] = this.value.split('-').map(Number);
      filterEpisodesByRange(start, end);
    });

    // Set default to first range
    if (select.options.length > 0) {
      select.selectedIndex = 0;
      const [start, end] = select.value.split('-').map(Number);
      filterEpisodesByRange(start, end);
    }
  });